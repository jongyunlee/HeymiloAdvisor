var mongoose = require('mongoose');

var CommentSchema = new mongoose.Schema({
    body: String,
    author: String,
    star: {type: Number, default: 0},
    product: {type: mongoose.Schema.Types.ObjectId, ref: 'Product'}
});

mongoose.model('Comment', CommentSchema);
