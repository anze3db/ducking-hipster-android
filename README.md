ducking-hipster-android
=======================

Ducking Hipster for android


Building with maven:

get maven-android-sdk-deployer
clone and mvn -P 4.2 install

you will need all the sdk stuff...

build a signed package with:

mvn clean install android:deploy -Psign -Dkeystore.pass=pass. -Dkeystore.path=~/documentation/ducking-hipster/key

