# grpc-master-worker
Docker 컨테이너에서 실행되는 워커 노드들은 호스트의 포트를 통해 접근할 수 있도록 설정했습니다.

각 서브프로젝트(master, worker)는 독립적인 `build.sbt` 파일을 가지고 있긴 하지만, 일단 내용은 다 똑같습니다.

## 써보기
윈도우 환경에서 wsl을 사용했습니다. 테스트 해보고 싶으신 분들은 아래와 같이 하시면 됩니다. 일단, [docker desktop](https://www.docker.com/products/docker-desktop/)도 설치하셔야 됩니다. 설치하면 자동으로 wsl과 연동됩니다.

### worker, master 컴파일
```bash
sbt compile
```
우선, worker랑 master 하위 폴더에 들어가서 컴파일해줍니다.

### worker.jar 빌드
```bash
sbt assembly
```
worker는 assembly로 .jar 파일도 만들어줍니다. 이렇게 만들어진 파일은 ``target/scala-2.13/`` 안에 저장되게 됩니다.

### docker 빌드
```bash
docker-compose build
docker-compose up -d
```
docker를 빌드합니다. 완료되면, up으로 백그라운드에서 실행합니다. 결과는 docker desktop에서 확인하실 수 있습니다.

<img src="https://raw.githubusercontent.com/nyeoglya/grpc-master-worker/refs/heads/main/docs/docker.png">

더 상세한 설정을 알고 싶으시면, compile은 ``docker/Dockerfile.worker``를 보시면 되고, up은 ``docker/docker-compose.yml``을 보시면 됩니다.

### master 실행
```bash
sbt run
```
master 하위 폴더로 돌아가서 run으로 실행해줍니다. 최종 실행 결과는 아래와 같습니다.

<img src="https://raw.githubusercontent.com/nyeoglya/grpc-master-worker/refs/heads/main/docs/result.png">
