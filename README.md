# grpc-master-worker
Docker 컨테이너에서 실행되는 워커 노드들은 호스트의 포트를 통해 접근할 수 있도록 설정했습니다.
각 서브프로젝트(master, worker)는 독립적인 `build.sbt` 파일을 가지고 있긴 하지만, 일단 내용은 다 똑같습니다.


## 써보기
테스트 해보고 싶으신 분들은 아래와 같이 하시면 됩니다. 일단, windows면 docker desktop도 설치하셔야 됩니다.

```bash
cd ../
sbt compile
```

```bash
sbt assembly
```

```bash
cd ../docker
docker-compose build
docker-compose up -d
```

```bash
cd ../master
sbt run
```
