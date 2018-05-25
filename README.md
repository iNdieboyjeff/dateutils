# dateutils

**dateutils** is a Java library for handling some common date/time functions.

The library is currently in the early stages of development and so the range of functionality may appear limited.

## Current Functionality
* Parse date `String` to `Date` objects
* Format `Date` objects as `String`
* Utility class with some common date processing and comparison functions

### Supported Formats
* ATOM
* JavaScript
* SQL
* Twitter

## About the Date Formats

### ATOM
See https://tools.ietf.org/html/rfc4287#section-3.3

Examples of ATOM formatted dates are:
* `2009-11-04T19:55:41Z`
* `2009-11-04T21:55:41+02:00`

### JavaScript
This parser/formatter will handle the default JavaScript date output format .

Examples of JavaScript formatted dates are:
* `Wed Mar 25 2015 01:00:00 GMT+0100 (CET)`
* `Fri May 25 2018 08:12:32 GMT+0200 (CEST)`

### SQL
Examples of SQL formatted dates are:
* `2009-06-04`
* `2009-6-4`

### Twitter
Examples of Twitter formatted dates are:
* `Thu Apr 06 15:24:15 +0000 2017`
* `Wed Aug 27 13:08:45 +0000 2008`


## The Utility Functions

Withing the `Dates` class are a series of handy functions for some common date/time related tasks.

### `parseDate`
This function will attempt to use the various Date format classes to parse a String to a Date. 

### `asAtomUTC`
A wrapper for the `AtomDate.formatAtomDate` function.  No timezone is specified so the output `String` will be in UTC.