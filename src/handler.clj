(ns handler
  (:require [db]
            [hiccup.core :refer [html]]
            [views.todo :as todo]
            [views.index :as index]))

(defn index []
  (html (index/render-page (db/todo-list))))

(defn new-todo [title]
  (html (todo/view (db/new-todo title))))

(defn update-todo [id title]
  (html (todo/view (db/update-todo id title))))

(defn toggle-todo [id]
  (html (todo/view (db/toggle-todo id))))

(defn delete-todo [id]
  (db/delete-todo id))
