const http = require('http');

const express = require('express');
const socketio = require('socket.io');

// const config = require('./config');
// const routes = require('./routes');

// y speed * -1 for player
// x speed * -1 for wall

const app = express();
const server = http.createServer(app);
const io = socketio(server);

/* ROUTES */
let waitingConnections = [];
io.on('connection', socket => {
  console.log('connection made');
  socket.on('join', () => {
    console.log('Request made to join');
    waitingConnections.push(socket);
    if (waitingConnections.length === 2) {
      waitingConnections.forEach(socket => socket.emit('start'));
      waitingConnections = [];
    }
  })
  socket.on('player_move', ({ direction }) => {
    io.emit('other_move', direction);
  })
  socket.on('ball_hit', ({ angle }) => {
    io.emit('ball_bounce', angle);
  })
  socket.on('disconnect', () => {
    console.log('disconnected');
    if (waitingConnections.indexOf(socket) > -1) {
      waitingConnections.splice(waitingConnections.indexOf(socket), 1);
    }
  })
})

app.get('/', (req, res) => res.end('Shai ur bad'));

server.listen(5000, '0.0.0.0', () => {
  console.log('Server Started!'); // eslint-disable-line no-console
});