#!/bin/bash

echo '>>>>>>>>>>>>>>>>>>>>>>>>>>>Cleaning project<<<<<<<<<<<<<<<<<<<<<<<<<<<'
mvn clean cobertura:clean
rc=$?
if [[ $rc != 0 ]] ; then
  echo 'could not clean project'; exit $rc
fi

echo '>>>>>>>>>>>>>>>>>>>>>>>>>>>   UT Coverage  <<<<<<<<<<<<<<<<<<<<<<<<<<<'
mvn cobertura:cobertura
rc=$?
if [[ $rc != 0 ]] ; then
  echo 'could not run UT coverage'; exit $rc
fi

echo '>>>>>>>>>>>>>>>>>>>>>>>>>>>   IT Coverage  <<<<<<<<<<<<<<<<<<<<<<<<<<<'
mvn verify -Dskip.surefire.tests
rc=$?
if [[ $rc != 0 ]] ; then
  echo 'could not run UT coverage'; exit $rc
fi

echo '>>>>>>>>>>>>>>>>>>>>>>>>>>> updating sonar <<<<<<<<<<<<<<<<<<<<<<<<<<<'
mvn sonar:sonar
rc=$?
if [[ $rc != 0 ]] ; then
  echo 'could not update sonar'; exit $rc
fi
