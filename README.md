# isbd-parser

**ISBD Parsing Library**

## Overview

isbd-parser is a Kotlin library for working with ISBD-punctutated data, using the [Autumn](https://github.com/norswap/autumn)
context-sensitive parsing library.

See the [wiki](https://github.com/hzafar/isbd-parser/wiki) for information on getting started.

Documentation available [here](https://javadoc.io/doc/ca.voidstarzero/isbd-parser).

## Try it out

You can play with an example application made using this library at [isbuddy.voidstarzero.ca](http://isbuddy.voidstarzero.ca).

## Installation

### Maven

```
<repositories>
    <!-- For the Autumn library dependency -->
    <repository>
        <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
  <groupId>ca.voidstarzero</groupId>
  <artifactId>isbd-parser</artifactId>
  <version>1.1.0</version>
</dependency>
```

### Gradle
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation "ca.voidstarzero:isbd-parser:1.1.0"
}
```



