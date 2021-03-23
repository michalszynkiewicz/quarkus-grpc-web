# How to use Grpc Web with Quarkus

This example demonstrates how to use `quarkus-grpc` extension with gRPC Web.
The Google variant of gRPC is used in this example, with `grpcwebtext` (equivalent of `plaintext` gRPC).

The example demonstrates both, unary and server-side stream streaming.
At the time of writing both, Google and Improbable, gRPC Web variants don't support client-side and bi-directional streaming.

Please note that the example uses Envoy Proxy to translate gRPC Web to native gRPC. We plan to add gRPC Web support to Quarkus but it is not implemented yet at the time of writing.


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

## Compiling and running the project

### UI
Get to the ui directory and run npm and npx in it:

```
cd ui
npm install
npx webpack client.js
```

### Backend application
The backend application is a maven project with `mvnw`, you can run it in the development mode with:
```
./mvnw compile quarkus:dev
```

You can find further information on packaging and running Quarkus applications, take a look at
[Quarkus - Creating Your First Application](https://quarkus.io/guides/getting-started#packaging-and-run-the-application) and [Quarkus - Building a Native Executable](https://quarkus.io/guides/building-native-image).

### Envoy proxy
As I mentioned in the introduction, Grpc Web requires a proxy to communicate with gRPC "native".

The easiest way to run Envoy proxy is to use Docker or Podman in the root directory of the project:
```
docker run -p 8080:8080 -v $(pwd)/envoy.yaml:/etc/envoy/envoy.yaml -e ENVOY_UID=$(id -u) envoyproxy/envoy:v1.17.0
```

This command will expose the envoy proxy on port 8080.

## The result
[mstodo: path to the index.html file]
To check out the results, open the `index.html` file in your browser.