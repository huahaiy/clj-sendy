# clj-sendy

A Clojure client for Sendy API

[Sendy](http://sendy.co/) is an email newsletter/marketing application that uses Amazon SES to send emails. 

Use this to work with Sendy in Clojure. Configurations are expected to be in the enviornment variables:

`SENDY_URL` is the base URL of the sendy installation, without trailing "/"
`LIST_ID` is the ID of the mailing list. 
`LIST_IDS` is a comma separated IDs of mailing lists to be targeted in a compaign.
`BRAND_ID` is the ID of the brand.
`API_KEY` is your API key.
