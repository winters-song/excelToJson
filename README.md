#excelToJson
This java code converts excel which stores translations in 7 languages to separated json files. 
  
Languages supports:
* Simple Chinese
* English
* Russian
* Korean
* Japanese
* Spanish
* French


##Usage
1. Edit Excel file according to your demand.
2. Edit java code as necessary. Export it as jar file and put it under the same folder with that Excel file named "lang.xls".
3. Run Jar file.


##Output
In the format of the following:
>{“id":"text", ”id2":“text2”...}


##Excel
* Translation starts from row 4 that has a "$start" mark.
* Translation ends with an "$end" mark.
* Column one contains the ids of translation items.
