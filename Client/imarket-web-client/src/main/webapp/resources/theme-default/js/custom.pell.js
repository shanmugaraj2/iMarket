function attachPellEditor(element){
	const editor = pell.init({
		element: element,
		onChange: html => {

			
			document.getElementById('description-output').textContent = html
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
}