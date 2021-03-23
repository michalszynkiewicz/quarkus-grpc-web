# How to use Grpc Web with Quarkus

This example demonstrates how to use `quarkus-grpc` extension with gRPC Web.
The Google variant of gRPC is used in this example, with `grpcwebtext` (equivalent of `plaintext` gRPC).

The example demonstrates both, unary and server-side stream streaming.
At the time of writing both, Google and Improbable, gRPC Web variants don't support client-side and bi-directional streaming.


## What's needed

To use the example you will need:
- JDK 11
- Node.js with `npx`,
- `protoc` and `protoc-gen-grpcweb` plugin for protoc
- Docker or Podman

source: https://grpc.io/docs/platforms/web/basics/

### Installing `protoc` and `protoc-gen-grpcweb`
You can install `protoc` by downloading and unzipping its latest release from [Protobuf releases](https://github.com/protocolbuffers/protobuf/releases/).

This project originally used the `3.15.6` version.

You will also need `protoc-gen-grpcweb` to generate `js` code for this demo. This `protoc` plugin can be downloaded from [gRPC Web Releases](https://github.com/grpc/grpc-web/releases). This project originally used version `1.2.1`.

To ease setup of the project, add `protoc`'s  bin directory and the location of `protoc-gen-grpcweb` to your `PATH`.


### Installing Node.js
One of the ways to install Node.js is to use the [Node Version Manager](https://github.com/nvm-sh/nvm) and run `nvm install node`.

Another element you'll need is `npx`. After installing node, install it with

```
npm install npx
```


##


##  
