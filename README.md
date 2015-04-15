<p align="center"><a href="http://geomesa.github.io"><img src="https://raw.githubusercontent.com/geomesa/geomesa.github.io/master/img/geomesa-2x.png"></img></a></p>

### Build Status

Version | Status
------ | -----
| 1.x for Accumulo 1.5.x | [![Build Status](https://travis-ci.org/locationtech/geomesa.svg?branch=accumulo1.5.x%2F1.x)](https://travis-ci.org/locationtech/geomesa)  	| 

## GeoMesa

![Splash](http://geomesa.github.io/img/geomesa-overview-848x250.png)

GeoMesa is an open-source, distributed, spatio-temporal database built on top of the Apache Accumulo column family store. GeoMesa implements standard Geotools interfaces to provide geospatial functionality over very large data sets to application developers.  GeoMesa provides plugins for exposing geospatial data stored in Accumulo via standards-based OGC HTTP services within the GeoServer administrative interface.

#### ![LocationTech](https://pbs.twimg.com/profile_images/2552421256/hv2oas84tv7n3maianiq_normal.png) GeoMesa is a member of the [LocationTech](http://www.locationtech.org) working group of the Eclipse Foundation.

## Download and Version Information

<b>NOTE:</b> The current recommended version is ```1.0.0-rc5``` which is a breaking change from ```1.0.0-rc4``` and previous versions due to schema incompatibility. The most recent tar.gz assembly can be [downloaded here](http://repo.locationtech.org/content/repositories/geomesa-releases/org/locationtech/geomesa/geomesa-assemble-accumulo1.5/1.0.0-rc.5/geomesa-assemble-accumulo1.5-1.0.0-rc.5-bin.tar.gz) which contains the [Accumulo distributed runtime jar](geomesa-distributed-runtime), [GeoServer plugin](geomesa-plugin), and [command line tools](geomesa-tools).

GeoMesa artifacts can be downloaded from the [LocationTech Maven repository](https://repo.locationtech.org/content/repositories/geomesa-releases/)

Snapshots are availabile in the [LocationTech Snapshots Repository](https://repo.locationtech.org/content/repositories/geomesa-snapshots/)


## Building Instructions

* Navigate to where you would like to download this project.
* ```git clone git@github.com:locationtech/geomesa.git```

This project is managed by Maven, and builds with the command

``` geomesa> mvn clean install ```

From the root directory, this builds each sub-project with its additional dependencies-included JAR.

## Documentation

* [Quick Start](http://geomesa.github.io/#quick-start) on the main [documentation](http://geomesa.github.io) site.
* [FAQ](http://geomesa.github.io/faq/)
* [Tutorials](http://geomesa.github.io)
* GeoMesa [Users](https://locationtech.org/mhonarc/lists/geomesa-users/) and [Dev](https://locationtech.org/mhonarc/lists/geomesa-dev/) mailing list archives
* READMEs are provided under most modules: [Tools](geomesa-tools), [Jobs](geomesa-jobs), etc

## Scala console via scala-maven-plugin

To test and interact with core functionality, the Scala console can be invoked in a couple of ways.  From the root directory by specifying geomesa-core ```geomesa> mvn -pl geomesa-core scala:console```.  Or from a given sub-project's directory ```geomesa-core> mvn scala:console```.  By default, all of the project packages in ```core``` are loaded along with JavaConversions, JavaConverters.

## GeoMesa Project Structure

#### geomesa-assemble

This sub-project assembles a tarball containing the commandline tools along with the distributed runtime, geoserver, and compute Jars with the associated readme files.

#### geomesa-compute

This sub-project contains utilities for working with distributed computing environments.  Currently, there are methods for instantiating an Apache Spark Resilient Distributed Dataset from a CQL query against data stored in GeoMesa.  Eventually, this project will contain bindings for traditional map-reduce processing, Scalding, and other environments.

#### geomesa-convert

This sub-prohect contains a configurable and extensible library for converting data sources in various formats into SimpleFeatures.

#### geomesa-core

This project contains the implementations of the core indexing structures, Accumulo iterators, and the GeoTools interfaces for exposing the functionality as a ```DataStore``` to both application developers and GeoServer.

#### geomesa-distributed-runtime

This sub-project assembles a jar with dependencies that must be distributed to Accumulo tablet servers' lib/ext
directory or to an HDFS directory where Accumulo's VFSClassLoader can pick it up.

#### geomesa-feature

This sub-project stores our implementation of a serializable SimpleFeature, including Kryo and Avro based implementations.

#### geomesa-filter

TODO: explain geomesa-filter

#### geomesa-jobs

This sub-project holds map/reduce and scalding jobs for maintaining GeoMesa. It includes jobs that transition tables between versions of GeoMesa when breaking changes are introduced.

#### geomesa-kafka

This sub-project contains our geomesa-kafka data store and builds the GeoServer plugin associated with it.

#### geomesa-plugin

This sub-project creates a plugin which provides WFS and WMS support.  The JAR named geomesa-plugin-<Version>-geoserver-plugin.jar is ready to be deployed in GeoServer by copying it into geoserver/WEB-INF/lib/ directory.

#### geomesa-process

This sub-project holds several analytic processes optimized for GeoMesa data stores including: KNN, Density, Temporal Density, and Tube Select among others.

#### geomesa-tools

This sub-project contains a set of command line tools for managing features, ingesting and exporting data, configuring tables, and explaining queries in GeoMesa. Please view the [geomesa-tools README](https://github.com/locationtech/geomesa/tree/accumulo1.5.x/1.x/geomesa-tools#geomesa-tools) to learn more.

#### geomesa-utils

This sub-project stores our GeoHash implementation and other general library functions unrelated to Accumulo.

#### geomesa-web

TODO: explain geomesa-web