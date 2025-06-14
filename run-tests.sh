#!/bin/bash

CONTAINER_NAME=selenoid
VIDEO_FOLDER=./report/video
REPORT_FOLDER=./report

rm -rf "$REPORT_FOLDER"
mkdir -p "$VIDEO_FOLDER"

docker run --rm \
  -e GRADLE_TASK="$GRADLE_TASK" \
  -e DB_LOGIN="$DB_LOGIN" \
  -e DB_PASSWORD="$DB_PASSWORD" \
  -e APP_LOGIN="$APP_LOGIN" \
  -e APP_PASSWORD="$APP_PASSWORD" \
  -e BROWSER_TYPE=chrome \
  -e BROWSER_SIZE=1920x1080 \
  -v "$PWD/report/allure-results:/app/build/allure-results" \
  -v "$HOME/.gradle:/home/gradle/.gradle" \
  --add-host=host.docker.internal:host-gateway \
  civil-registry-app

docker cp "$CONTAINER_NAME:/opt/selenoid/video/." "$VIDEO_FOLDER"
docker exec $CONTAINER_NAME sh -c 'rm -f /opt/selenoid/video/*.mp4'
