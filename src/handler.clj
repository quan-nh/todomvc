(ns handler
  (:require [db]
            [hiccup.core :refer [html]]
            [views.todo :as todo]
            [views.index :as index]))

(defn index []
  (html (index/render-page (db/todo-list))))

(defn new-todo [title]
  (html (todo/view (db/new-todo title))
        (todo/items-left (db/todo-list))))

(defn update-todo [id title]
  (html (todo/view (db/update-todo id title))))

(defn toggle-todo [id]
  (let [todo (db/toggle-todo id)
        todos (db/todo-list)]
    (html (todo/view todo)
          (todo/items-left todos)
          (todo/clear-completed-btn todos))))

(defn delete-todo [id]
  (db/delete-todo id)
  (let [todos (db/todo-list)]
    (html (todo/items-left todos)
          (todo/clear-completed-btn todos))))

(defn clear-completed-todo []
  (db/remove-completed-todo)
  (let [todos (db/todo-list)]
    (html (todo/list todos)
          (todo/items-left todos)
          (todo/clear-completed-btn todos))))
