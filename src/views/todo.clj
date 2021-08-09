(ns views.todo)

(defn view [{:keys [id value completed?]}]
  [:li {:id (str "todo-" id)
        :class (when completed? "completed")}
   [:div.view
    [:input.toggle {:checked (when completed? "checked")
                    :type "checkbox"}]
    [:label {:hx-get (str "/todos/edit/" id)
             :hx-target (str "#todo-" id)
             :hx-swap "outerHTML"} value]
    [:button.destroy]]])

(defn edit [{:keys [id value]}]
  [:li {:id (str "todo-" id)
        :class "editing"}
   [:form {:hx-post (str "/todos/update/" id)
           :hx-target (str "#todo-" id)
           :hx-swap "outerHTML"}
    [:input.edit {:type "text"
                  :name "value"
                  :value value}]]])

(defn list [todos]
  [:ul.todo-list
   (for [todo todos]
     (view todo))])
