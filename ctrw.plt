set terminal pdfcairo size 29cm,21cm font "Times-New-Roman" fontscale 1.
set output "histogram.pdf"
set xlabel "{/:Italic x}"
set ylabel "{/:Italic P}_{/:Italic x}"
set title "Continuous Time Random Walk"
set style fill solid border lc rgb "black"
plot "ctrw.txt" with boxes lw 2 notitle
