
$(document).ready(function() {
    // Generate a simple captcha
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

    $('#defaultForm').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '无效的用户名',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空',
                    },
                    stringLength: {
                        min: 4,
                        max: 12,
                        message: '用户名长度需为4到12个字符',
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能是数字和字母'
                    },
                   
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            account: {
                message: '无效的用户名',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空',
                    },
                    stringLength: {
                        min: 4,
                        max: 12,
                        message: '用户名长度需为4到12个字符',
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能是数字和字母'
                    },
                   
                    different: {
                        field: 'password',
                        message: '用户名和密码不能相同'
                    }
                }
            },
          ID: {
                message: '无效的用户名',
                validators: {
                    notEmpty: {
                        message: '身份证不能为空',
                    },
                    stringLength: {
                        min: 18,
                        max: 18,
                        message: '身份证长度需为18个字符',
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '身份证只能是数字和字母'
                    },
                    remote: {
                        url: 'remote.php',
                        message: '无效的用户名'
                    }


                }
            },
            
            rname: {
                validators: {
                	 notEmpty: {
                         message: '真实姓名不能为空',
                     }                   
                }
            },
            
            email: {
                validators: {
                	 notEmpty: {
                         message: '邮箱不能为空',
                     },
                    emailAddress: {
                        message: '邮箱格式不正确'
                    }
                }
            },
            
            pass: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    
                    different: {
                        field: 'username',
                        message: '密码和用户名不能相同'
                    }
                }
            },
          password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '两次密码输入不同'
                    },
                    different: {
                        field: 'username',
                        message: '密码和用户名不能相同'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '请再次输入密码'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不同'
                    },
                    different: {
                        field: 'username',
                        message: '密码和用户名不能相同'
                    }
                }
            },
            birthday: {
                validators: {
                	 notEmpty: {
                         message: '出生日期不能为空',
                     },
                    date: {
                        format: 'YYYY/MM/DD',
                        message: '无效的出生日期'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: '请选择性别'
                    }
                }
            }
        }
    });

    // Validate the form manually
    $('#validateBtn').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
    });

    $('#resetBtn').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });
});

