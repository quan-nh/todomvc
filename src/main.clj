(ns main
  (:require [compojure.core :refer [defroutes GET POST PUT PATCH DELETE]]
            [compojure.route :as route]
            [compojure.coercions :refer [as-int]]
            [handler]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]))

(defroutes routes
  (GET "/" [] (handler/index))
  (POST "/todos" [title] (handler/new-todo title))
  (PUT "/todos/:id" [id :<< as-int title] (handler/update-todo id title))
  (PATCH "/todos/:id" [id :<< as-int] (handler/toggle-todo id))
  (DELETE "/todos/:id" [id :<< as-int] (handler/delete-todo id))
  (DELETE "/todos/completed" [] (handler/clear-completed-todo))
  (route/not-found "Page not found"))

(defn run [opts]
  (-> routes
      wrap-params
      (run-jetty {:port 3000})))
