
    document.addEventListener('DOMContentLoaded', function() {

    var canvas = document.getElementById('myCanvas');
    var ctx = canvas.getContext('2d');

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
         color += letters[Math.floor(Math.random() * 16)];
            }
        return color;
    }

    lineSegments.forEach(function(segment) {
        ctx.beginPath();
        ctx.moveTo(segment.x1, segment.y1);  // Startkoordinaten
        ctx.lineTo(segment.x2, segment.y2);  // Endkoordinaten
        ctx.strokeStyle=getRandomColor();
        ctx.lineWidth=3;
        ctx.stroke();
    });
    });
