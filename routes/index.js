var path = require('path');
var passport = require('passport');
var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Product = mongoose.model('Product');
var Comment = mongoose.model('Comment');
var User = mongoose.model('User');
var jwt = require('express-jwt');

var auth = jwt({secret: 'SECRET', userProperty: 'payload'});

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
});

router.get('/landing', function(req, res, next) {
    res.sendFile(path.join(__dirname, '../public/landing.html'));
});

router.get('/products', function(req, res, next) {
   Product.find(function(err, products) {
       if (err) { return next(err); }
       res.json(products);
   });
});

router.post('/products', auth, function(req, res, next) {
    var product = new Product(req.body);
    product.save(function(err, product) {
	if (err) { return next(err); }
	res.json(product);
    });
});

router.param('product', function(req, res, next, id) {
    var query = Product.findById(id);
    query.exec(function(err, product) {
	if (err) { return next(err); }
	if (!product) { return next(new Error('can\'t find post')); }
	req.product = product;
	return next();
    });
});

router.get('/products/:product', function(req, res) {
    // res.json(req.product);
    req.product.populate('comments', function(err, product) {
	if (err) { return next(err); }
	res.json(product);
    });
});

router.post('/products/:product/comments', auth, function(req, res, next) {
    var comment = new Comment(req.body);
    comment.product = req.product;
    comment.author = req.payload.user_id;
    comment.save(function(err, comment) {
	if (err) { return next(err); }
	req.product.comments.push(comment);
	req.product.save(function(err, product) {
	    if (err) { return next(err); }
	    res.json(comment);
	});
    });
});

router.post('/register', function(req, res, next) {
    if (!req.body.user_id || !req.body.password) {
	return res.status(400).json({ message: 'Please fill out all fields' });
    }
    var user = new User();
    user.user_id = req.body.user_id;
    user.setPassword(req.body.password);

    user.save(function (err) {
	if (err) {
	    console.log('err : ' + err);
	    return next(err);
	}
	return res.json({token: user.generateJWT()});
    });
});

router.post('/login', function(req, res, next) {
    if (!req.body.user_id || !req.body.password) {
	return res.status(100).json({ message: 'Please fill out all fields' });
    }
    passport.authenticate('local', function(err, user, info) {
	if (err) { return next(err); }
	if (user) {
	    return res.json({token: user.generateJWT()});
	} else {
	    return res.status(401).json(info);
	}
    })(req, res, next);
});

module.exports = router;
