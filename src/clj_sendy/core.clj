(ns clj-sendy.core
  (:require [environ.core       :refer [env]]
            [clj-http.client    :as client]))

(def api-key (env :sendy-api-key))
(def sendy-url (env :sendy-url))

(defn subscribe
  ([email list-id]
   (subscribe email list-id {}))
  ([email list-id opts]
   (client/post
    (str sendy-url "/subscribe")
    {:form-params
     (let [params {:api_key api-key
                   :email   email
                   :list    list-id
                   :boolean true}]
       (merge params opts))})))

(defn unsubscribe
  [email list-id]
  (client/post
   (str sendy-url "/unsubscribe")
   {:form-params
    {:email   email
     :list    list-id
     :boolean true}}))

(defn delete
  [email list-id]
  (client/post
   (str sendy-url "/api/subscribers/delete.php")
   {:form-params
    {:api_key api-key
     :email   email
     :list    list-id}}))

(defn status
  [email list-id]
  (client/post
   (str sendy-url "/api/subscribers/subscription-status.php")
   {:form-params
    {:email   email
     :list_id list-id
     :api_key api-key}}))

(defn subscriber-count
  [list-id]
  (client/post
   (str sendy-url "/api/subscribers/active-subscriber-count.php")
   {:form-params
    {:list_id list-id
     :api_key api-key}}))

(defn campaign
  "list-ids is a comma separated string. If send? is false, only campaign draft
  will be created"
  [{:keys [from-name from-email reply-to title subject plain-text html-text
           list-ids segment-ids exclude-list-ids exclude-segments-ids
           brand-id query-string send?]}]
  (client/post
   (str sendy-url "/api/campaigns/create.php")
   {:form-params
    {:api_key              api-key
     :from_name            from-name
     :from_email           from-email
     :reply_to             reply-to
     :title                title
     :subject              subject
     :plain_text           plain-text
     :html_text            html-text
     :list_ids             list-ids
     :segment_ids          segment-ids
     :exclude_list_ids     exclude-list-ids
     :exclude_segments_ids exclude-segments-ids
     :brand_id             brand-id
     :query_string         query-string
     :send_campaign        (if send? 1 0)}}))
