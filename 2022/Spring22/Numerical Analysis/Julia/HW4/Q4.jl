#Also used this file for quiz 3

using FundamentalsNumericalComputation

"""
newton(f,dfdx,x1)

Use Newton's method to find a root of `f` starting from `x1`, where
`dfdx` is the derivative of `f`. Returns a vector of root estimates.
"""
function newton(f, dfdx, x1)
    # Operating parameters.
    funtol = 100 * eps()
    xtol = 100 * eps()
    maxiter = 40

    x = [x1]
    y = f(x1)
    dx = Inf   # for initial pass below
    k = 1

    while (abs(dx) > xtol) && (abs(y) > funtol) && (k < maxiter)
        dydx = dfdx(x[k])
        dx = -y / dydx            # Newton step
        push!(x, x[k] + dx)        # append new estimate

        k = k + 1
        y = f(x[k])
    end

    if k == maxiter
        @warn "Maximum number of iterations reached."
    end

    return x
end

fourpi = 4*pi

d = x -> (x^(-2)-sin(x))
ddx = x -> (-cos(x)-(2)/(x^3))
plt = plot([d],l=3,label=["\$y=f(x)\$" "\$y=x\$"],aspect_ratio=1,
    title="Q4",legend=:bottomright, xlims=(.2, fourpi))

display(plt)

#println(nlsolve(x->d(x[1]),[0.]).zero) 

#print(nlsolve(x->d(x[1]),[1.]).zero)
display(newton(d,ddx,1.5))
display(newton(d,ddx,2.0))
display(newton(d,ddx,3.2))
display(newton(d,ddx,4.0))
display(newton(d,ddx,5.0))
display(newton(d,ddx,2.0*pi))