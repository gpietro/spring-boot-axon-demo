# PoC Axon Framework for ADT Patient solution following

This PoC aims to evaluate the usage of Axon Framework for the development of an Admission Dismission Transfer Patient
solution follwing the CQRS pattern.

### Run Axon Server SE with docker

```
docker run -d --rm --name axonserver-se -p 8024:8024 -p 8124:8124 \
       -v `pwd`/data:/data \
       -v `pwd`/events:/eventdata \
       -v `pwd`/config:/config \
       axoniq/axonserver
```

### Run application with docker (not working yet)

**Build image**

```docker build -t cqrs-adt-demo .```

**Run the application**

```docker run -p 8080:8080 cqrs-adt-demo```