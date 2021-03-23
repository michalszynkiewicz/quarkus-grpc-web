const {EchoRequest, ServerStreamingEchoRequest, EchoResponse} = require('./dist/proto/echo_pb.js');
const {EchoServiceClient} = require('./dist/proto/echo_grpc_web_pb.js');

var echoService = new EchoServiceClient('http://localhost:8080');
/* 
var request = new EchoRequest();
request.setMessage('Hello World!');





echoService.echo(request, {}, function(err, response) {
  // ...
}); */


var streamingRequest = new ServerStreamingEchoRequest();
streamingRequest.setMessage("message for streaming");

var stream = echoService.serverStreamingEcho(streamingRequest, {});
stream.on('data', function(response) {
  console.log(response.getMessage());
})