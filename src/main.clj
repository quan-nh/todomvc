(ns main
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.coercions :refer [as-int]]
            [db]
            [hiccup.core :refer [html]]
            [ring.adapter.jetty :refer [run-jetty]]
            [views.index]
            [views.todo]))

(defroutes routes
  (GET "/" [] (views.index/render-page (db/get-todos)))
  (GET "/todos/edit/:id" [id :<< as-int] (html (views.todo/edit (db/get id))))
  (POST "/todos/update/:id" [id :<< as-int] (html (views.todo/view (db/get id))))
  (route/not-found "Page not found"))

(defn run [opts]
  (run-jetty routes {:port 3000}))
