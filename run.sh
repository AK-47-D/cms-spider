#!/bin/bash
export GRADLE_HOME=/Users/alilang/soft/gradle-5.5
# 解决freemark乱码  -Dfile.encoding=UTF-8
$GRADLE_HOME/bin/gradle clean bootRun -Dfile.encoding=UTF-8