/**
 * @author 陈侃(cknet@126.com, 13910090885)
 *         https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
Object.extend($UI, {

  doCategoryPopItems : function(btn, ele, e) {
    if (!(btn = $(btn)))
      return;
    btn.observe(e || "mouseover", function(ev) {
      if (btn._pop) {
        $Effect.show(btn._pop);
        return;
      }

      if (!document.body.contains(ele))
        document.body.appendChild(btn._pop = ele);

      var pos = $UI.getPopupOffsets(ele, btn);
      ele.setStyle("top: " + (pos[1]) + "px; left: " + (pos[0]) + "px");
      $UI.CategoryPopItems_init(ele);
      $Effect.show(ele);

      ele.observe("mouseover", function(ev) {
        ele._active = true;
      });
      ele.observe("mouseleave", function(ev) {
        ele.hide();
        ele._active = false;
      });
    });

    btn.observe("mouseleave", function(ev) {
      (function() {
        if (ele) {
          if (ele._active) {
            return;
          }
          ele.hide();
          ele._active = false;
        }
      }).delay(0.1)
    });
  },

  CategoryPopItems_init : function(ele) {
    var items = ele.select(".p_item");

    var ele_h = ele.getHeight();
    var ele_top = parseInt(ele.style.top);
    items.invoke("observe", "mouseover", function(ev) {
      var sub = this.down(".p_sub");

      var w = this.getWidth();
      var sub_h = sub.getHeight();
      var top = this.cumulativeOffset()[1];
      if (top + sub_h > ele_h + ele_top)
        top = ele_h + ele_top - sub_h;
      top = Math.max(top - ele_top, 0);

      var delta = 1;
      if (Browser.IEVersion <= 8) {
        delta = 2;
      }
      sub.setStyle("top: " + (top - delta) + "px; left:" + (w) + "px");

      var p = this.previous();
      if (p)
        p.addClassName("p_sep2");
      var n = this.next();
      if (n)
        n.addClassName("p_sep2");

      this.down(".p_bg").show();
      sub.show();
    });
    items.invoke("observe", "mouseleave", function(ev) {
      var p = this.previous();
      if (p)
        p.removeClassName("p_sep2");
      var n = this.next();
      if (n)
        n.removeClassName("p_sep2");

      this.down(".p_bg").hide();
      this.down(".p_sub").hide();
    });
  },

  // Pagelet Tab
  doPageletTab : function(params, obj) {
    var act = $Actions['PageletTab_ajax'];

    var tab = obj.up(".tab") || obj;
    var lc = tab.up(".pagelets").down(".lc");
    var tabs = tab.up(".tabs").select(".tab");

    var _update = function(_content) {
      lc.update(_content);

      tabs.each(function(_tab) {
        _tab.removeClassName("active");
      });
      tab.addClassName("active");

      return _content;
    };

    if (tab._content) {
      _update(tab._content);
    } else {
      act.jsCompleteCallback = function(req, responseText, json) {
        tab._content = _update(responseText);
      };
      act(params);
    }
  },

  doListRowsTooltip : function(selector) {
    if (!window.Tip)
      return;
    $$(selector).each(function(ti) {
      var d = ti.down('.tt');
      if (!d)
        return;
      new Tip(ti, d.innerHTML, {
        style : 'darkgrey',
        stem : 'rightTop',
        delay : 0.8,
        hideOthers : true,
        hideAfter : 0.5,
        width : 380,
        hook : {
          target : 'leftTop',
          tip : 'rightTop'
        },
        hideOn : {
          element : 'tip',
          event : 'mouseleave'
        }
      });
    });
  }
});