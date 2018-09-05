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
  
  let xSpeed;
  let ySpeed;

  socket.on('join', () => {
    console.log('Request made to join');
    waitingConnections.push(socket);
    if (waitingConnections.length === 2) {
      waitingConnections.forEach(socket => socket.emit('start'));
      waitingConnections = [];
    }
  })
  socket.on('player_move', ({ direction }) => {
    socket.broadcast.emit('other_move', direction);
  })
  socket.on('ball_wall_bounce', () => {
    ySpeed *= -1;
    socket.broadcast.emit('ball_wall_bounced', {
      xSpeed,
      ySpeed,
    })
  })
  socket.on('ball_hit', () => {
    xSpeed *= -1;
    socket.broadcast.emit('ball_bounce', {
      xSpeed,
      ySpeed,
    });
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