# Cash manager mobile


## Docker

You can build and serve the apk with docker !

```bash
docker build -t cashmanager-mobile . # build docker image
docker run -p 80:80 cashmanager-mobile # run a new container
```

You now can find the app at `http://localhost/cashmanager.apk`