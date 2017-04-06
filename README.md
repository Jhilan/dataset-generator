## Dataset Generator
a small tool that generates custom data sets of documents with metadata, it's useful for testing purposes
in systems that deals with json based documents and need to have custom data sets of any size.

### Usage
- clone this repository
- $ ./build.sh

a sample dataset will be generated at ./dist/dataset1.json as configured in ./dist/config.yml

To generate a custom data set:

1. customize your data set in the ./dist/config.yml file, you may add as many fields with data of the available types
   there's examples in the yml file of what types of fields and their values looks like that you may need.

2. you will need to provide the directory path that is expressed in the config.yml as 'textBaseDirectory' property
(this is a directory that contains text file/files from where the program will extract the 'bodyText' field in a random way
,the more text files the better the text field uniqueness gets.)
