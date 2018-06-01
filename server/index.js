const http = require('http');

const express = require('express');
const socketio = require('socket.io');

// const config = require('./config');
// const routes = require('./routes');

const app = express();
const server = http.createServer(app);
const io = socketio(server);

/* ROUTES */
io.on('connection', socket => {
  console.log('connection made');
  socket.on('join', () => {
    if (io.sockets.length === 2) {
      io.emit('start');
    }
  })
  socket.on('move', ({ direction }) => {
    io.emit('other_move', direction);
  })
  socket.on('ball_hit', ({ angle }) => {
    io.emit('ball_bounce', angle);
  })
})

server.listen(8080, '192.168.1.169', () => {
  console.log('Server Started!'); // eslint-disable-line no-console
});