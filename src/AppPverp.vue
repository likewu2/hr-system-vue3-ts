<script>
export default {
  mounted() {
    document.title = 'PV ERP';
    this.executeScriptCode();
  },
  data() {
    return {
      scriptSources: [
        "/web/com.pvtool.erp.userinterface.smartclient/isomorphic/ISC_Combined.js",
        "/web/com.pvtool.erp.userinterface.smartclient/isomorphic/ISC_History.js",
        "/com.pvtool.erp.client.kernel/OBCLKER_Kernel/StaticResources?_appName=OB3&_skinVersion=Default"
      ]
    };
  },
  methods: {
    executeScriptCode() {
      // Define the error handler
      window.onerror = function indexErrorHandler(errorMsg, url, lineNumber) {
        var msg = errorMsg + ' - ' + url + ':' + lineNumber;
        var loadingDiv = document.getElementById('OBLoadingDiv');
        if (loadingDiv && loadingDiv.parentNode) {
          loadingDiv.parentNode.removeChild(loadingDiv);
        }
        alert(msg);
        return true;
      };

      // Override document.write to handle async loading issues
      const originalWrite = document.write;
      const originalWriteln = document.writeln;
      let documentWriteBuffer = '';
      
      document.write = function() {
        // In async mode, buffer the content instead of writing directly
        const content = Array.prototype.join.call(arguments, '');
        documentWriteBuffer += content;
        console.warn('document.write() called asynchronously. Content buffered:', content);
      };
      
      document.writeln = function() {
        const content = Array.prototype.join.call(arguments, '') + '\n';
        documentWriteBuffer += content;
        console.warn('document.writeln() called asynchronously. Content buffered:', content);
      };

      // Set LABjs global defaults
      if (window.$LAB) {
        $LAB.setGlobalDefaults({AppendTo: 'body'});
      }

      // Define OBStartApplication function
      window.OBStartApplication = function() {
        // Process any buffered document.write content
        if (documentWriteBuffer) {
          console.log('Processing buffered document.write content');
          const div = document.createElement('div');
          div.innerHTML = documentWriteBuffer;
          document.body.appendChild(div);
          documentWriteBuffer = '';
        }
        
        if (window.OB && window.OB.Layout) {
          OB.Layout.initialize();
          OB.Layout.draw();
          OB.Layout.ViewManager.createAddStartTab();
          var loadingDiv = document.getElementById('OBLoadingDiv');
          if (loadingDiv && loadingDiv.parentNode) {
            loadingDiv.parentNode.removeChild(loadingDiv);
          }
          if (document.forms.OBGlobalHiddenForm) {
            window.OB.GlobalHiddenForm = document.forms.OBGlobalHiddenForm;
          }
        }
      };

      // Use LABjs to load scripts in sequence
      if (window.$LAB) {
        console.log('Loading scripts with LABjs');
        let chain = $LAB;
        this.scriptSources.forEach((src, index) => {
          console.log(`Loading script ${index + 1}:`, src);
          chain = chain.script(src);
        });
        
        // Wait for all scripts to load, then start the application
        chain.wait(() => {
          console.log('All scripts loaded');
          // Restore document.write after scripts are loaded
          document.write = originalWrite;
          document.writeln = originalWriteln;
          
          // Process any remaining buffered content
          if (documentWriteBuffer) {
            console.log('Processing final buffered content');
            const div = document.createElement('div');
            div.innerHTML = documentWriteBuffer;
            document.body.appendChild(div);
            documentWriteBuffer = '';
          }
          
          if (typeof window.OBStartApplication === 'function') {
            console.log('Starting OB application');
            window.OBStartApplication();
          } else {
            console.error('OBStartApplication function not defined');
          }
        });
      } else {
        console.error("LABjs not available");
        // Restore document.write
        document.write = originalWrite;
        document.writeln = originalWriteln;
        
        // Process any buffered content
        if (documentWriteBuffer) {
          const div = document.createElement('div');
          div.innerHTML = documentWriteBuffer;
          document.body.appendChild(div);
          documentWriteBuffer = '';
        }
        
        // Fallback: try to start after a delay
        console.log('Using fallback timeout to start application');
        setTimeout(() => {
          if (typeof window.OBStartApplication === 'function') {
            window.OBStartApplication();
          }
        }, 3000);
      }
    }
  }
}
</script>

<template>
  <div class="OBLoadingPromptModalMask" id="OBLoadingDiv">
    <div class="OBCenteredBox">
        <table>
          <tbody>
            <tr>
                <td>
                    <span class="OBLoadingPromptLabel">LOADING...</span>
                </td>
                <td>
                    <img width="220" height="16" src="http://m2ddns.pvtool.com:8080/web/com.pvtool.erp.userinterface.smartclient/openbravo/skins/Default/com.pvtool.erp.client.application/images/system/windowLoading.gif"/>
                </td>
            </tr>
          </tbody>
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