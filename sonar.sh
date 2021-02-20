#!/usr/bin/env bash

export sk_ling__sonar_dir=$(readlink -f "$(dirname "${BASH_SOURCE[0]}")")
if [ ${SK_EXP__SONAR__TOKEN} ]; then
  cd ${sk_ling__sonar_dir}
  mvn sonar:sonar -Dsonar.host.url=http://sonar.shaneking.org -Dsonar.login=${SK_EXP__SONAR__TOKEN}
else
  echo 'SK_EXP__SONAR__TOKEN not exist.'
fi
cd ${sk_ling__sonar_dir}
