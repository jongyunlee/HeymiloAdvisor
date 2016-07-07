var mongoose = require('mongoose');

var ProductSchema = new mongoose.Schema({
    name: String,
    price: Number,
    reviewCount: {type: Number, default: 0},
    star:  {type: Number, default: 0},
    comments: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Comment'}]
});

mongoose.model('Product', ProductSchema);
