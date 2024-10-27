// build.sbt 공통 설정
scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty-shaded" % "1.56.0",
  "io.grpc" % "grpc-protobuf" % "1.56.0",
  "io.grpc" % "grpc-stub" % "1.56.0",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % "0.11.13"
)

Compile / PB.protoSources := Seq(baseDirectory.value / ".." / "proto")

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)
