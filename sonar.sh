#!/usr/bin/env bash

export sk_ling__sonar_dir=$(readlink -f "$(dirname "${BASH_SOURCE[0]}")")
if [ ${SK_EXP__SONAR__TOKEN} ]; then
  cd ${sk_ling__sonar_dir}
  mvn sonar:sonar -Dsonar.host.url=http://sonar.shaneking.org -Dsonar.login=${SK_EXP__SONAR__TOKEN}
  sk_ling_modules=("org.shaneking.ling.cache" "org.shaneking.ling.jackson" "org.shaneking.ling.jsqlparser" "org.shaneking.ling.persistence" "org.shaneking.ling.rr" "org.shaneking.ling.test" "org.shaneking.ling.zero")
  for sk_ling_module in ${sk_ling_modules[@]}; do
    if [ -d ${sk_ling__sonar_dir}/${sk_ling_module} ]; then
      cd ${sk_ling__sonar_dir}/${sk_ling_module}
      mvn sonar:sonar -Dsonar.host.url=http://sonar.shaneking.org -Dsonar.login=${SK_EXP__SONAR__TOKEN}
    else
      echo "${sk_ling__sonar_dir}/${sk_ling_module} not exist."
    fi
  done
else
  echo 'SK_EXP__SONAR__TOKEN not exist.'
fi
cd ${sk_ling__sonar_dir}
