(ns views.todo)

(defn new []
  [:form {:hx-post "/todos"
          :hx-target "#todo-list"
          :hx-swap "beforeend"
          :_ "on htmx:afterOnLoad set #new-todo.value to ''"}
   [:input#new-todo.new-todo
    {:name "title" :autofocus "autofocus" :placeholder "What needs to be done?"}]])

(defn view [{:keys [id title completed?]}]
  [:li {:id (str "todo-" id)
        :class (when completed? "completed")}
   [:div.view
    [:input.toggle {:type "checkbox"
                    :checked (when completed? "checked")
                    :hx-patch (str "/todos/" id)
                    :hx-target (str "#todo-" id)
                    :hx-swap "outerHTML"}]
    [:label {:_ (str "on dblclick add .editing to #todo-" id)} title]
    [:button.destroy {:hx-delete (str "/todos/" id)
                      :_ (str "on htmx:afterOnLoad remove #todo-" id)}]]
   [:form {:hx-put (str "/todos/" id)
           :hx-target (str "#todo-" id)
           :hx-swap "outerHTML"}
    [:input.edit {:type "text"
                  :name "title"
                  :value title}]]])

(defn list [todos]
  [:ul#todo-list.todo-list
   (for [todo todos]
     (view todo))])

(defn items-left [todos]
  [:span#todo-count.todo-count {:hx-swap-oob "true"}
   [:strong (->> todos (remove :completed?) count)]
   " item left"])
