## Run application with docker
**Build image (not working yet)**

```docker build -t cqrs-adt-demo .```

**Run the application**

```docker run -p 8080:8080 cqrs-adt-demo```

**Run Axon Server SE**
```
docker run -d --rm --name axonserver-se -p 8024:8024 -p 8124:8124 \
       -v `pwd`/data:/data \
       -v `pwd`/events:/eventdata \
       -v `pwd`/config:/config \
       axoniq/axonserver
```