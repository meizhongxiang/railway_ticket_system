export const ajaxPost = (url, params, options, fn) => {
  let results = null;

  if (typeof options === 'function' && arguments.length <= 3) {
    fn = options;
    options = {};
  }
  this.$http.interceptors.push((request, next) => {
    request.credentials = true;
    next();
  });
  this.$http.post(url, params, options).then((response) => {
    if (response.ok) {
      results = response.body;
      fn(1, results);
    } else {
      fn(0, results);
    }
  }, (error) => {
    if (error) {
      fn(0, results);
    }
  })
};

const ajaxGet = (url, fn) => {
  let results = null;
  Vue.http.get(url, { credentials: true }).then((response) => {
    if (response.ok) {
      results = response.body;
      fn(1, results);
    } else {
      fn(0, results);
    }
  }, (error) => {
    if (error) {
      fn(0, results);
    }
  });
};

export default {
  ajaxPost,
  ajaxGet
}
