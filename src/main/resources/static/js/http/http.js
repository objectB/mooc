
const apiService = axios.create({timeout:100*12})
//添加拦截器
apiService.interceptors.request.use(
   config => {
     const  token = localStorage.getItem("token");
     if(!config.headers['Content-Type']){
         config.headers ={
             'Content-Type':'application/json'
         }
     }
     if(token){
         config.headers.Authorization=token
     }
     if(config.method === 'get'){
         config.pasrams = config.pasrams || {};
     }
     config.data = JSON.stringify(config.data)

     return config
   },
    error => {
       return Promise.reject(error)
    }

)

apiService.interceptors.response.use(
    response =>{
        if(response.data.code == 200){
           return Promise.resolve(response.data)

         }else{
             return Promise.reject(response)
         }
    },
    error=>{
        return Promise.reject(error)
    }
)

function get(url,params) {
    return  new Promise((resolve ,reject) =>{
         apiService.get(url,{
             params:params
         }).then(res => {
             resolve(res)
         }).catch(err =>{
             reject(err)
         })
    })
}

function post(url,params) {
    return new Promise((resolve,reject)=>{
         apiService.post(url,params)
             .then(res=>{
                 resolve(res)
             }).catch(err =>{
                 console.log()
                reject(err)
         })
    })

}









