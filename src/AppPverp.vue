<script>
export default {
  mounted() {
    document.title = 'PV ERP';
    
    const link1 = document.createElement('link');
    link1.rel = 'stylesheet';
    link1.href = 'http://m2ddns.pvtool.com:8080/com.pvtool.erp.client.kernel/OBCLKER_Kernel/StyleSheetResources?_appName=OB3&_skinVersion=Default&_cssDataUri=true';
    document.head.appendChild(link1);
    
    const script1 = document.createElement('script');
    script1.src = 'http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.client.kernel/js/scopeleaks.min.js';
    document.head.appendChild(script1);
    
    const script2 = document.createElement('script');
    script2.src = 'http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.client.kernel/js/LAB.min.js';
    script2.onload = () => {
      // Now that LABjs is loaded, we can use $LAB
      $LAB.setGlobalDefaults({AppendTo: 'body'});

      var isomorphicDir='http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.userinterface.smartclient/isomorphic/';
      var isc = window.isc ? window.isc : {};

      function OBStartApplication() {
        OB.Layout.initialize();
        OB.Layout.draw();
        OB.Layout.ViewManager.createAddStartTab();
        document.body.removeChild(document.getElementById('OBLoadingDiv'));
        OB.GlobalHiddenForm = document.forms.OBGlobalHiddenForm;
      }

      const script4 = document.createElement('script');
      script4.src = 'http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.userinterface.smartclient/isomorphic/ISC_Combined.js';
      document.head.appendChild(script4);
      const script5 = document.createElement('script');
      script5.src = 'http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.userinterface.smartclient/isomorphic/ISC_History.js';
      document.head.appendChild(script5);
      const script6 = document.createElement('script');
      script6.src = 'http://m2ddns.pvtool.com:8080/com.pvtool.erp.client.kernel/OBCLKER_Kernel/StaticResources?_appName=OB3&_skinVersion=Default';
      document.head.appendChild(script6);
    };
    script2.onerror = () => {
      console.error('Failed to load LAB.js');
    };
    document.head.appendChild(script2);
    const script3 = document.createElement('script');
    script3.src = 'http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.client.kernel/js/BigDecimal-all-1.0.1.min.js';
    document.head.appendChild(script3);
    
    (function () {
      var b = document.documentElement;
      b.setAttribute('data-useragent',  navigator.userAgent);
      b.setAttribute('data-platform', navigator.platform );
      b.className += ((!!('ontouchstart' in window) || !!('onmsgesturechange' in window))?' touch':'');
    }());

    window.onerror = function indexErrorHandler(errorMsg, url, lineNumber) {
      var msg = errorMsg + ' - ' + url + ':' + lineNumber;
      document.body.removeChild(document.getElementById('OBLoadingDiv'));
      alert(msg);
    };
  }
}
</script>

<template>
  <div class="OBLoadingPromptModalMask" id="OBLoadingDiv">
    <div class="OBCenteredBox">
        <table>
            <tr>
                <td>
                    <span class="OBLoadingPromptLabel">LOADING...</span>
                </td>
                <td>
                    <img width="220" height="16" src="http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.userinterface.smartclient/openbravo/skins/Default/com.pvtool.erp.client.application/images/system/windowLoading.gif"/>
                </td>
            </tr>
        </table>
    </div>
  </div>
  <iframe name="background_target" id="background_target" height="0" width="0" style="display:none;"></iframe>
  <form name="OBGlobalHiddenForm" method="post" action="blank.html" target="background_target">
  </form>
</template>

<style>
  html, body {
      height: 100%;
      width: 100%;
  }

  .OBCenteredBox {
      position: fixed;
      z-index: 1000000;
      top: 50%;
      left: 50%;
      margin: -25px 0 0 -150px;
      width: 300px;
      height: 50px;
  }

  .OBLoadingPromptLabel {
      font-family: 'Arial';
      font-size: 12px;
      color: #ccd0d4;
  }

  .OBLoadingPromptModalMask {
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: #7f7f7f;
  }
</style>