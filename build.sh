#!/usr/bin/env bash

if [ -z "$NVM_DIR" ]; then  # This checks if nvm is already loaded (it isn't in IntelliJ)
  echo "Loading NVM..."
  export NVM_DIR="$HOME/.nvm"

  if [ ! -f "$NVM_DIR/nvm.sh" ]; then
    echo "Error: NVM not found!"
    echo "Please install NVM before runing this script."
    exit 1;
  fi
  \. "$NVM_DIR/nvm.sh"  # This loads nvm
  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
  echo "Done loading NVM!"
fi

cd ./frontend/
npm run compile
cd ..

cd ./backend/
mvn clean install
cd ..
cp ./backend/target/backend-*.jar ./backend.jar

cd ./frontend/
npm run clean
cd ..
