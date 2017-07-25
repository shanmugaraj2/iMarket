<!DOCTYPE html>
<html lang="en">
<head>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pell.min.css">
</head>
<body>
	<div id="pell"></div>
	<div>
	  Text output:
	  <div id="text-output"></div>
	  HTML output:
	  <pre id="html-output"></pre>
	</div>
</body>
  <script src="<%=request.getContextPath()%>/js/pell.min.js"></script> 
<script type = 'text/javascript'>

const editor = pell.init({
	  element: document.getElementById('pell'),
	  onChange: html => {
		 
	    document.getElementById('text-output').innerHTML = html
	    document.getElementById('html-output').textContent = html
	  },
	  styleWithCSS: true,
	  actions: [
	    'bold',
	    'underline',
	    'strikethrough',
	    'olist',
	    'ulist',
	    'heading1',
	    'heading2',
	    'paragraph',
	    {
	      name: 'italic',
	      result: () => window.pell.exec('italic')
	    },
	    {
	      name: 'custom',
	      icon: '<b><u><i>C</i></u></b>',
	      title: 'Custom Action',
	      result: () => console.log('YOLO')
	    },
	    {
	      name: 'image',
	      result: () => {
	        const url = window.prompt('Enter the image URL')
	        if (url) window.pell.exec('insertImage', url)
	      }
	    },
	    {
	      name: 'link',
	      result: () => {
	        const url = window.prompt('Enter the link URL')
	        if (url) window.pell.exec('createLink', url)
	      }
	    }
	  ],
	  classes: {
	    actionbar: 'pell-actionbar-custom-name',
	    button: 'pell-button-custom-name',
	    content: 'pell-content-custom-name'
	  }
	})
	 
	// editor.content<HTMLElement> 
	// To change the editor's content: 
	//editor.content.innerHTML = '<b><u><i>Initial content!</i></u></b>'
</script>
<style>
.pell-content-custom-name {
  height: 400px;
}
</style>
</html>
