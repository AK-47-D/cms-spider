#!/bin/bash
export GRADLE_HOME=/Users/alilang/soft/gradle-4.2.1
# 解决freemark乱码  -Dfile.encoding=UTF-8
$GRADLE_HOME/bin/gradle clean bootRun -Dfile.encoding=UTF-8