# clj-sendy

[Sendy](http://sendy.co/) is an email newsletter/marketing application that uses Amazon SES to send emails.  This Clojure client for Sendy provides a few functions to work with Sendy. 

Configurations are expected to be in the following enviornment variables:

- `SENDY_URL` is the base URL of the Sendy installation, without the trailing "/"
- `SENDY_API_KEY` is the Sendy API key.

#Installation

clj-sendy is available from Clojars:

[clj-sendy "0.2.0"]
