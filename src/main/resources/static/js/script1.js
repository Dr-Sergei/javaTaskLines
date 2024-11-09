// Wartet, bis das DOM vollständig geladen ist, bevor das Skript ausgeführt wird
document.addEventListener('DOMContentLoaded', function() {

    // Initialisiert das Canvas-Element und den 2D-Zeichenkontext
    const canvas = document.getElementById('myCanvas');
    const ctx = canvas.getContext('2d');

    /**
     * Generiert eine zufällige Farb-Hexadezimalzeichenfolge, die als Strichfarbe verwendet wird
     * @returns {string} Eine Zufallsfarbe im Hexadezimalformat (z.B. #3F6A92)
     */
    function getRandomColor() {
        const letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    // Punktzählung zur Erkennung von Überschneidungen
    // Verwendet ein Objekt, um zu zählen, wie oft jeder Punkt in den Liniensegmenten vorkommt
    const pointCounts = {};
    lineSegments.forEach(segment => {
        const startKey = `${segment.x1},${segment.y1}`;
        const endKey = `${segment.x2},${segment.y2}`;

        // Erhöht den Zähler für Start- und Endpunkte des Segments
        pointCounts[startKey] = (pointCounts[startKey] || 0) + 1;
        pointCounts[endKey] = (pointCounts[endKey] || 0) + 1;
    });

    // Zeichnet alle Liniensegmente und markiert Schnittpunkte
    lineSegments.forEach(segment => {
        // Zeichnet eine Linie vom Startpunkt zum Endpunkt
        ctx.beginPath();
        ctx.moveTo(segment.x1, segment.y1);
        ctx.lineTo(segment.x2, segment.y2);
        ctx.strokeStyle = getRandomColor();  // Zufällige Farbe für jede Linie
        ctx.lineWidth = 3;
        ctx.stroke();

        // Überprüft, ob am Start- oder Endpunkt eine Überschneidung von 3 Linien besteht
        const startKey = `${segment.x1},${segment.y1}`;
        const endKey = `${segment.x2},${segment.y2}`;

        // Zeichnet einen weißen Kreis an Punkten, wo genau 3 Linien zusammenkommen
        if (pointCounts[startKey] === 3) {
            drawCircle(ctx, segment.x1, segment.y1);
        }
        if (pointCounts[endKey] === 3) {
            drawCircle(ctx, segment.x2, segment.y2);
        }
    });

    /**
     * Zeichnet einen weißen Kreis an einer gegebenen Position (x, y), um einen Schnittpunkt zu markieren
     * @param {CanvasRenderingContext2D} ctx - Der 2D-Kontext des Canvas
     * @param {number} x - Die x-Koordinate des Schnittpunkts
     * @param {number} y - Die y-Koordinate des Schnittpunkts
     */
    function drawCircle(ctx, x, y) {
        ctx.beginPath();
        ctx.arc(x, y, 12, 0, 2 * Math.PI); // Radius = 12 für den Markierungskreis
        ctx.fillStyle = 'white';           // Markierungsfarbe für Überschneidungspunkte
        ctx.fill();
    }
});
