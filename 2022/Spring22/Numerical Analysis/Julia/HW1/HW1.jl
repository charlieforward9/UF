using FundamentalsNumericalComputation

#Question 1 Functions!
function abserror(approx, actual)
    return abs(approx - actual)
end
function relerror(approx,actual)
    return abserror(approx,actual) / actual
end
function accurateDigits(approx, actual)
    return floor(-log10(relerror))
end
