(ns main
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [views.index]))

(defroutes routes
  (GET "/" [] (views.index/render-page))
  (route/not-found "Page not found"))

(defn run [opts]
  (run-jetty routes {:port 3000}))
