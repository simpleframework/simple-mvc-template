/**
 * @author 陈侃(cknet@126.com, 13910090885) https://github.com/simpleframework
 *         http://www.simpleframework.net
 */
Object.extend($UI, {

  doCategoryPopItems : function(btn, ele, delta) {
    if (!(btn = $(btn)))
      return;
    btn.observe("mouseenter", function(ev) {
      if (btn._pop) {
        $Effect.show(btn._pop);
        return;
      }

      if (!document.body.contains(ele))
        document.body.appendChild(btn._pop = ele);

      var pos = $UI.getPopupOffsets(ele, btn);
      delta = delta || [ 0, 0 ];
      var left = pos[0] + delta[0];
      var top = pos[1] + delta[1];
      ele.setStyle("top: " + top + "px; left: " + left + "px");
      $UI.CategoryPopItems_init(ele);
      $Effect.show(ele);
    });
  },

  CategoryPopItems_init : function(ele) {
    var items = ele.select(".p_item");

    var doActive = function(item, remove) {
      var ope = remove ? "removeClassName" : "addClassName";
      item[ope]("active");
      var p = item.previous();
      if (p)
        p[ope]("p_sep2");
      var n = item.next();
      if (n)
        n[ope]("p_sep2");
    };

    var ele_h = ele.getHeight();
    var ele_top = parseInt(ele.style.top);

    var setTop = function(item) {
      var sub = item.down(".p_sub");
      var sub_h = sub.getHeight();
      var top = item.cumulativeOffset()[1];

      if (top + sub_h > ele_h + ele_top)
        top = ele_h + ele_top - sub_h;
      top = Math.max(top - ele_top, 0);

      var delta = 1;
      if (Browser.IEVersion <= 8) {
        delta = 2;
      }
      sub.setStyle("top: " + (top - delta) + "px;left: " + item.getWidth()
          + "px");
    };

    var _show = function(item) {
      doActive(item);
      item.down(".p_bg").show();
      item.down(".p_sub").show();
    };
    var _hide = function(item) {
      doActive(item, true);
      item.down(".p_bg").hide();
      item.down(".p_sub").hide();
    };

    items.each(function(item) {
      item.observe("mouseenter", function(ev) {
        item._enter = true;
        (function() {
          if (!item._enter)
            return;

          var last = ele._last;
          if (last) {
            _hide(last);
          }
          // 设置位置
          setTop(item);
          _show(item);
          ele._last = item;
        }).delay(0.1);
      }).observe("mouseleave", function(ev) {
        item._enter = false;
      });
    });

    var hideEle = function() {
      if (!ele._active) {
        ele.hide();
        var last = ele._last;
        if (last) {
          _hide(last);
        }
      }
    };
    ele.observe("mouseenter", function(ev) {
      ele._active = true;
    }).observe("mouseleave", function(ev) {
      ele._active = false;
      hideEle.delay(0.2);
    });
    document.observe("click", function(ev) {
      hideEle();
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