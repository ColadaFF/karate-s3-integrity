Feature: file upload end-point

  Background:
    * url 'http://localhost:8080'

  Scenario: upload file
    Given def id = 'file.csv'
    And path 'files', id
    And multipart file file = { read: 'file.csv', filename: 'file.csv', contentType: 'text/csv' }
    When method post
    Then status 200

    # MD5 of file, as explained in https://aws.amazon.com/premiumsupport/knowledge-center/data-integrity-s3/
    * def fileMD5Hash = '8n+s73aCT1uuqZOJj1s+FQ=='
    # example of calling custom java code from karate
    * def FileChecker = Java.type('software.ias.FileChecker')
    # example of parsing a string into json by karate
    * def s3Md5 = FileChecker.getMd5FromS3(id)
    * match fileMD5Hash == s3Md5
