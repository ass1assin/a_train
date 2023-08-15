function api(data){
    return $axios({
       'url': '/main',
        'method': 'post',
        data
    })

}