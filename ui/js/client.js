const {EchoRequest, ServerStreamingEchoRequest, EchoResponse} = require('./dist/proto/echo_pb.js');
const {EchoServiceClient} = require('./dist/proto/echo_grpc_web_pb.js');

var echoService = new EchoServiceClient('http://localhost:8080');

document.getElementById("trigger-unary").onclick = function() {
  let input = document.getElementById("unary-message").value;
  var echoRequest = new EchoRequest();
  echoRequest.setMessage(input);

  let resultDiv = document.getElementById("unary-result");
  resultDiv.innerHTML = "";
  echoService.echo(echoRequest, {}, function(err, response) {
        var li = document.createElement("li");
        li.innerHTML = response.getMessage();

        resultDiv.appendChild(li);
  });
}

document.getElementById("trigger-stream").onclick = function() {
  let input = document.getElementById("stream-message").value;
  let interval = document.getElementById("stream-delay").value;
  let count = document.getElementById("stream-count").value;
  var streamingRequest = new ServerStreamingEchoRequest();
  streamingRequest.setMessage(input);
  streamingRequest.setMessageInterval(interval);
  streamingRequest.setMessageCount(count);

  let resultDiv = document.getElementById("stream-result");
  resultDiv.innerHTML = "";
  var stream = echoService.serverStreamingEcho(streamingRequest, {});

  stream.on('data', function(response) {
    var li = document.createElement("li");
    li.innerHTML = response.getMessage();

    resultDiv.appendChild(li);
  })
}
