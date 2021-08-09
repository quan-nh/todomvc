(ns views.index
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [views.todo]))

(defn render-page [todos]
  (html5 {:lang "en"}
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:content "width=device-width, initial-scale=1", :name "viewport"}]
          [:title "TodoMVC"]
          (include-css "https://unpkg.com/todomvc-app-css@2.4.1/index.css")
          (include-js "https://unpkg.com/htmx.org@1.5.0")]
         [:body
          [:section.todoapp
           [:header.header
            [:h1 "todos"]
            [:input.new-todo
             {:autofocus "autofocus", :placeholder "What needs to be done?"}]]
           "<!-- This section should be hidden by default and shown when there are todos -->"
           [:section.main
            [:input#toggle-all.toggle-all {:type "checkbox"}]
            [:label {:for "toggle-all"} "Mark all as complete"]
            (views.todo/list todos)]
           "<!-- This footer should be hidden by default and shown when there are todos -->"
           [:footer.footer
            "<!-- This should be `0 items left` by default -->"
            [:span.todo-count [:strong "0"] " item left"]
            "<!-- Remove this if you don't implement routing -->"
            [:ul.filters
             [:li [:a.selected {:href "#/"} "All"]]
             [:li [:a {:href "#/active"} "Active"]]
             [:li [:a {:href "#/completed"} "Completed"]]]
            "<!-- Hidden if no completed items are left ↓ -->"
            [:button.clear-completed "Clear completed"]]]
          [:footer.info
           [:p "Double-click to edit a todo"]
           "<!-- Remove the below line ↓ -->"
           [:p
            "Template by "
            [:a {:href "http://sindresorhus.com"} "Sindre Sorhus"]]
           "<!-- Change this out with your name and url ↓ -->"
           [:p "Created by " [:a {:href "http://todomvc.com"} "you"]]
           [:p "Part of " [:a {:href "http://todomvc.com"} "TodoMVC"]]]]))
