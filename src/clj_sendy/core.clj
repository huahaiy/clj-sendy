(ns clj-sendy.core
  (:require [environ.core       :refer [env]]
            [clj-http.client    :as client]))

(defn subscribe
  [email list-id & {:keys [name]}]
  (client/post 
   (str (env :sendy-url) "/subscribe")
   {:form-params 
    (let [params {:email email :list list-id :boolean true}] 
      (if name (merge params {:name name}) params))}))

(defn unsubscribe
  [email list-id]
  (client/post 
   (str (env :sendy-url) "/unsubscribe")
   {:form-params 
    {:email email :list list-id :boolean true}}))

(defn status
  [email list-id]
  (client/post 
   (str (env :sendy-url) "/api/subscribers/subscription-status.php")
   {:form-params 
    {:email email :list_id list-id :api_key (env :api-key)}}))

(defn subscriber-count
  [list-id]
  (client/post 
   (str (env :sendy-url) "/api/subscribers/active-subscriber-count.php")
   {:form-params 
    {:list_id list-id :api_key (env :api-key)}}))

(defn campaign
  "list-ids is a comma separated string. If send? is false, only campaign draft
  will be created"
  [from-name from-email reply-to subject
   plain-text html-text list-ids brand-id send?]
  (client/post 
   (str (env :sendy-url) "/api/campaigns/create.php")
   {:form-params 
    {:api_key (env :api-key) 
     :from_name from-name :from_email from-email
     :reply_to reply-to :subject subject :plain_text plain-text
     :html_text html-text 
     :list_ids list-ids 
     :brand_id brand-id 
     :send_campaign (if send? 1 0)}}))
